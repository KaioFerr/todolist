package br.com.kaioferreira.todolist.application.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    //metodo que copia os valores da fonte e passar para um alvo
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //metodo que buscas a propiedades e gera um array
    public static String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source); //Interface para acessar as propiedades do objeto

        PropertyDescriptor[] pds = src.getPropertyDescriptors(); //minhas propiedades
        Set<String> emptyNames = new HashSet<>();

        //para cada pd em pds, será pego o nome e colocado no emptyNames, se for nulo
        for (PropertyDescriptor pd : pds){
            Object srcValue = src.getPropertyValue((pd.getName()));
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

        //Transforma o resultado em um array
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
