package com.nnk.maven.test.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nnk.maven.test.handler.Handler;
import com.nnk.maven.test.properties.PropertiesMapping;
import com.nnk.maven.test.util.DateUtil;
import com.nnk.maven.test.vo.PayVo;

@Controller
@RequestMapping("/hrbb2c")
public class PayController {

	private static final Logger log = LoggerFactory.getLogger(PayController.class);

	@Autowired
	private Handler handler;

	/**
	 * 
	 * @param orderid
	 *            订单号
	 * @param amount
	 *            交易金额
	 */
	@RequestMapping("/pay")
	public String pay(@RequestParam("orderid") String orderid, @RequestParam("amount") String amount, HttpServletRequest request, HttpServletResponse response) {
		log.debug("开始进入支付： 订单号 = {},交易金额 = {}", orderid, amount);
		// List<AppBean> apps = new ArrayList<AppBean>();
		// ConfBean confBean = new ConfBean();
		Map<String, Object> properties = PropertiesMapping.getProperties("hrbb2c");
		// confBean.setHost(properties.get("SignAndVerifyHost") + "");
		// confBean.setPort(Integer.valueOf(properties.get("SignAndVerifyPort")
		// + ""));
		// confBean.setAppName(properties.get("AppName") + "");
		// confBean.setPwd(properties.get("AppPwd") + "");
		// confBean.setUser(properties.get("AppUser") + "");
		// AppBean appBean = new AppBean();
		// appBean.setCmd("Sign");

		log.debug(this.getClass().getResource("/").getPath() + "/msgclient.xml");
		// MsgSrvShortConnector msgSrvShortConnector = new
		// MsgSrvShortConnector(this.getClass().getResource("/").getPath() +
		// "/msgclient.xml");
		String tranData = "TranAbbr=" + properties.get("TranAbbr") + "|OrderId=" + orderid + "|MerchantDateTime=" + DateUtil.getCurrentTime() + //
				"|TransAmount=" + amount + "|MerchantUrl=" + properties.get("merchantUrl") + "|MerchantId=" + properties.get("merchantId") + //
				"|TermCode=" + properties.get("TermCode") + "|Remark1=|Remark2=";
		log.debug("订单签名数据原文 : {}", tranData);
		// String msg = Base64Util.encodeStr(tranData);
		// msg = properties.get("AndVerifyAppName") + " Sign NA " + msg;
		// log.debug("发送签名数据 : {}", msg);
		// String result = msgSrvShortConnector.send(msg);
		String result = handler.sign(tranData);
		log.debug("签名返回数据 : {}", result);
		// String[] resultArr = result.split(" ");
		// if (resultArr[2] != "200" || resultArr.length < 3) {
		// log.debug("签名失败 : {}", result);
		// return;
		// }
		// String signData = Base64Util.decodeStr(resultArr[3]);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("transName", properties.get("transName"));
		params.put("Plain", tranData);
		params.put("Signature", result);
		StringBuffer dataForm = new StringBuffer("<form method='post' action='" + properties.get("payUrl") + "' name='HEBForm'>");
		Set<Entry<String, Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			dataForm.append("<input type='hidden' name='" + entry.getKey() + "' id='" + entry.getKey() + "' value='" + entry.getValue() + "'>");
		}
		dataForm.append("</form><script language='javascript'>document.forms[0].submit();</script>");
		log.debug("发往银行的表单 : {}", dataForm.toString());
		try {
			// response.getWriter().write(dataForm.toString());
		} catch (Exception e) {
			log.error("向银行发送数据失败 : ", e);		}
		PayVo vo = new PayVo(properties.get("payUrl") + "", properties.get("transName") + "", tranData, result);
		request.setAttribute("pay", vo);
		return "redirect:/jsp/aa.jsp";
	}
	
}
