package by.gsu.epamlab.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.model.enums.Section;

public class SectionChooseFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String sectionStr = httpRequest.getParameter(ConstantsJSP.KEY_SECTION);
		
		if (sectionStr == null) {
			HttpSession session = httpRequest.getSession(false);
			Section section = (Section) session.getAttribute(ConstantsJSP.KEY_SECTION);
			sectionStr = section.getName();
		}
		
		httpRequest.setAttribute(ConstantsJSP.KEY_SECTION_ATTRIBUTE, sectionStr);
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
