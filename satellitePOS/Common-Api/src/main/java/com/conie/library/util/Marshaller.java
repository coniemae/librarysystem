/*
 * (c) Copyright 2013 Total Soft Tech Solutions, Inc.
 *  All rights reserved. All other trademarks and copyrights referred to herein
 *  are the property of their respective holders. No part of this code may be
 *  reproduced in any form or by any means or used to take any derivative work,
 *  without written permission from Total Soft Tech Solutions, Inc.
 */
package com.conie.library.util;

/**
 * @author Joemar S Matulac <joemar.matulac@totalsofttech.com.ph>
 *
 */
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class Marshaller {

	private static final Logger log = Logger.getLogger(Marshaller.class);
	private static Marshaller marshaller;
	
	private Marshaller() {}
	
	static {
		synchronized (Marshaller.class) {
			if(null == marshaller)
				marshaller = new Marshaller();
		}
	}
	
	public synchronized static Marshaller getInstance() {
		return marshaller;
	}
	
	public synchronized String convertToJSONString(Object per) {
        String mappedStr = null;
        Writer strWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(strWriter, per);
            mappedStr = null == strWriter.toString() ? "" : strWriter.toString();
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
        } catch (IOException e){
        	log.error(e.getMessage());
        } finally {
            try {
            	mapper=null;
                strWriter.flush();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return mappedStr;
    }

    public synchronized <T> Object convertToObject(String obj, Class<T> castTo) {
        Object p = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            p = mapper.readValue(obj, castTo);
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch(JsonMappingException e) {
        	log.error(e.getMessage());
        }catch (IOException e) {
            log.error(e.getMessage());
        }
        mapper = null;
        return p;
    }
    
    public synchronized List<?> convertToObjLst(String jsonString, TypeReference<?> tp){
        List<?> t = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            t = mapper.readValue(jsonString,  tp);
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch(JsonMappingException e) {
        	log.error(e.getMessage());
        }catch (IOException e) {
            log.error(e.getMessage());
        }
        mapper = null;
        return t;
    }
}
