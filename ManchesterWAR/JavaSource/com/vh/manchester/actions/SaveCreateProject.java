/**
 * @file SaveCreateProject
 * @author peter.szocs
 * @version 1.0
 * 
 * Save action for creating projects.
 */


package com.vh.manchester.actions;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.User;
import com.vh.manchester.service.ManchesterService;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.RequestUtils;

/**
 * @version 	1.0
 * @author		peter.szocs
 */
public class SaveCreateProject extends BaseAction {

	private static Logger log = Logger.getLogger(SaveCreateProject.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ManchesterService man) throws Exception {
		//if(IS_DEBUG) log.debug("inside");

		if(!isCancelled(request)) {
			DynaValidatorForm projectform = (DynaValidatorForm) form;
			String desc = (String) projectform.get("desc");
			Integer[] users  = (Integer[]) projectform.get("userId"); //only checked users show up here!!
			Integer[] access = (Integer[]) projectform.get("access"); //ALL accesses show up here!! (ie length mismatch with users array) 

			/*
			log.debug("access length="+access.length);
			log.debug("users length="+users.length);
			for(int i=0;i<access.length;i++) log.debug("access["+i+"]="+access[i]);
			for(int i=0;i<users.length;i++)  log.debug("users["+i+"]="+users[i]);
			*/
			
			ActionErrors errors = new ActionErrors();
			// validating user checkboxes
			if((users!=null) &&(0<users.length)) {
				// validating whether access is set for each checked user
				Integer[] access2 = new Integer[users.length]; //access2 defaults to null! (all elements)
				int j=0; //j is the indexer of access2.  We basically copy values from access into access2 such that users and access2 have same length AND corresponding values.
				for(int i=0;i<users.length;i++) {
					while(j<access.length) {
						if(access[j].intValue()!=-1) {
							access2[i] = access[j];
							j++;
							break;
						}
						j++;
					}
					if((j==access.length) && (access2[i]==null)) errors.add("errors.access", new ActionError("errors.access", man.findUserById(users[i]).getUsername()));
				}
				
				// create project, add users to the new project
				if(errors.isEmpty()) {
					Project newProject = man.createProject(desc);
					for(int i=0;i<users.length;i++)	man.createProjectAccess(newProject.getId(), users[i], access2[i]);
				}													
			} else errors.add("errors.proj1", new ActionError("errors.proj1"));

			// forward based on whether there are errors present
			if(!errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.findForward("failure");
			} else {
				//update session constants
				ServletContext context = this.getServlet().getServletContext();
				context.setAttribute(Constants_Scope.ALL_PROJECTS_KEY, man.findAllProject());				
				RequestUtils.setProjectsChanged(request, 1);
				return mapping.findForward("success");
			} 
		}

		return mapping.findForward("success");
	}
}