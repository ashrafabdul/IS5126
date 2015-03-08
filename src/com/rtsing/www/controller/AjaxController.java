package com.rtsing.www.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






import com.google.gson.Gson;
import com.rtsing.www.bo.HeatMapBo;
import com.rtsing.www.dto.HeatMapData;
import com.rtsing.www.json.RequestJson;
import com.rtsing.www.json.ResponseJson;


@WebServlet({ "/rtsing" })
public class AjaxController extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Request String
		String jsonString = "";
		try {

			StringBuilder sb = new StringBuilder();

			String s;
			while ((s = req.getReader().readLine()) != null) {
				sb.append(s);
			}
			jsonString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gsonReq = new Gson();

		String operation = "default";
		String ajaxResponse = "default";

		RequestJson jsonObject = gsonReq.fromJson(jsonString, RequestJson.class);

		operation = jsonObject.getOperation();

		if (operation != null && operation.equals("updateHeatMap")) {
			
			HeatMapBo hmBo = new HeatMapBo();
			HeatMapData hmd = hmBo.getHeatMapData();
			ResponseJson responseObj = new ResponseJson();
			responseObj.setMax(100);
			responseObj.setData(hmd.getDataPoints());
			Gson gsonRes = new Gson();
			ajaxResponse = gsonRes.toJson(responseObj);
		}
		
		resp.setContentType("text/json");
		PrintWriter out = resp.getWriter();
		out.write(ajaxResponse);
		out.close();

	}

	

}
