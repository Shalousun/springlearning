package com.boco.sunyu.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class DataSourceAspect {
    protected static final ThreadLocal<String> preDatasourceHolder = new ThreadLocal<String>();


    @Pointcut("execution(* com.boco.*.dao.*.*(..))")
    public void datasourceAspect() {

    }



    /**
     * 根据@ChooseDataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
     */
    @Before("datasourceAspect()")
    public void changeDataSourceBeforeMethodExecution(JoinPoint jp) {
        String key = determineDatasource(jp);
        if (key == null) {
            DatasourceContextHolder.setDatasourceType(null);
            return;
        }
        preDatasourceHolder.set(DatasourceContextHolder.getDatasourceType());
        DatasourceContextHolder.setDatasourceType(key);

    }

    /**
     *
     * @param jp
     * @return
     */
    protected String determineDatasource(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class targetClass = jp.getSignature().getDeclaringType();
        String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
        String dataSourceForTargetMethod = resolveDataSourceFromMethod(
                targetClass, methodName);
        String resultDS = determinateDataSource(dataSourceForTargetClass,
                dataSourceForTargetMethod);
        return resultDS;
    }


    /**
     *
     */
    @After("datasourceAspect()")
    public void restoreDataSourceAfterMethodExecution() {
        DatasourceContextHolder.setDatasourceType(preDatasourceHolder.get());
        preDatasourceHolder.remove();
    }


    /**
     *
     * @param targetClass
     * @param methodName
     * @return
     */
    private String resolveDataSourceFromMethod(Class targetClass,
                                               String methodName) {
        Method m = findUniqueMethod(targetClass, methodName);
        if (m != null) {
            DataSourceKey choDs = m.getAnnotation(DataSourceKey.class);
            return resolveDataSourceName(choDs);
        }
        return null;
    }

    /**
     *
     * @param classDS
     * @param methodDS
     * @return
     */
    private String determinateDataSource(String classDS, String methodDS) {
        return methodDS == null ? classDS : methodDS;
    }

    /**
     *
     * @param targetClass
     * @return
     */
    private String resolveDataSourceFromClass(Class targetClass) {
        DataSourceKey classAnnotation = (DataSourceKey) targetClass
                .getAnnotation(DataSourceKey.class);
        return null != classAnnotation ? resolveDataSourceName(classAnnotation) : null;
    }

    /**
     *
     * @param ds
     * @return
     */
    private String resolveDataSourceName(DataSourceKey ds) {
        return ds == null ? null : ds.value();
    }

    /**
     *
     * @param clazz
     * @param name
     * @return
     */
    private static Method findUniqueMethod(Class<?> clazz, String name) {
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
            for (Method method : methods) {
                if (name.equals(method.getName())) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }
}
