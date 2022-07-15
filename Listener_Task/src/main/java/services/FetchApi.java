package services;
import com.msf.log.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FetchApi extends HttpServlet {
	public static Logger log = Logger.getLogger(FetchApi.class);
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out= response.getWriter();
			 HttpSession session = request.getSession(false);
	         out.println("session found with "+session.getId());
	         out.println("session found with "+session.getLastAccessedTime());
	         log.debug("FetchApiServlet::HttpSession session called");
	         
			URL url = new URL("http://bajajfinsecapis.cmots.com/api/CompanyBackground/227");
			 log.info("FetchApi::URL created");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			log.info("FetchApi::Api connection is established");
			
			InputStream ip = httpUrlConnection.getInputStream();
			BufferedReader br1 = new BufferedReader(new InputStreamReader(ip));
			StringBuilder response1 = new StringBuilder();
			String responseSingle = null;
			int responseCode = httpUrlConnection.getResponseCode();
			if(responseCode !=200) {
				
			}else {
				while((responseSingle = br1.readLine())!=null) {
					response1.append(responseSingle);
				}
				String str = response1.toString();
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(str);  
				out.println(json);
			}
			log.info("FetchApi::Api access successful");
		}catch (Exception e) {
			e.printStackTrace();
			log.error("FetchApiServlet::unknown url problem"+e.getMessage());
		}}}
