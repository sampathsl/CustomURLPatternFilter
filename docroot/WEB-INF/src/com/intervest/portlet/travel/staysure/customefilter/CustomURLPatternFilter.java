package com.intervest.portlet.travel.staysure.customefilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

public class CustomURLPatternFilter implements Filter {

	@Override
	public void destroy() {
		LOG.info("#####CustomURLPatternFilter.destroy()");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI = request.getRequestURI();
		try {
			String[] urlPaths = StringUtil.split(requestURI, StringPool.FORWARD_SLASH);
			
			System.out.println(urlPaths[0]);
			System.out.println(urlPaths[1]);
			System.out.println(urlPaths[2]);
			System.out.println(urlPaths[3]);
			System.out.println(urlPaths[4]);
			System.out.println(urlPaths[5]);
			
			if (urlPaths.length == 6) {
				
				String forwardPath = "/web/guest/travel/hotel?p_p_id=flightsearch_WAR_hotelportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view"
						+ "&_flightsearch_WAR_hotelportlet_param1=" + urlPaths[1]
						+ "&_flightsearch_WAR_hotelportlet_param2=" + urlPaths[2]
						+ "&_flightsearch_WAR_hotelportlet_param3=" + urlPaths[3]
						+ "&_flightsearch_WAR_hotelportlet_param4=" + urlPaths[4]
						+ "&_flightsearch_WAR_hotelportlet_param5=" + urlPaths[5];
				
				//http://localhost:8080/web/guest/travel/hotel?p_p_id=flightsearch_WAR_hotelportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&
				//_flightsearch_WAR_hotelportlet_param1=Lanzarote&
				//_flightsearch_WAR_hotelportlet_param2=Costa%20Teguise&_flightsearch_WAR_hotelportlet_param3=Hotel%20Beatriz%20Costa%20&%20Spa
				
				System.out.println("forwardPath: " + forwardPath);
				
				req.getRequestDispatcher(forwardPath).forward(req, res);
			} else {
				chain.doFilter(req, res);
			}
		} catch (Exception e) {
			chain.doFilter(req, res);
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("Called SampleFilter.init(" + filterConfig + ")");
	}

	private static final Log LOG = 
			LogFactoryUtil.getLog(CustomURLPatternFilter.class);

}
