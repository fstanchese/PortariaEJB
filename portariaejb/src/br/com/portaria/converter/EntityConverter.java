package br.com.portaria.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.portaria.util.BaseEntity;

@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facescontext, UIComponent component, String value) {
        if (value != null) { 
            return this.getAttributesFrom(component).get(value);  
        }  
        return null;
	}

	@Override
	public String getAsString(FacesContext facescontext, UIComponent component, Object value) {
        if (value != null && !"".equals(value)) {  
  
            BaseEntity entity = (BaseEntity) value;  
  
            this.addAttribute(component, entity);  
  
            Long codigo = entity.getId();  
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return (String) value;  
	}
	
    protected void addAttribute(UIComponent component, BaseEntity entity) {  
        String key = entity.getId().toString(); 
        this.getAttributesFrom(component).put(key, entity);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }
}
