/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.utilidades;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.context.SessionMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 */
public class Session {
    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static Map<String, Object> getSessionMap(){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
}
