package cn.mucfc.com.checkModel.dynamicdatasource.configuration;


import cn.mucfc.com.checkModel.dynamicdatasource.common.DataSourceKey;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DynamicDataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static Lock lock = new ReentrantLock();

    private static int counter = 0;

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.defaultLocal::name);


    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();


    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * Use defaultLocal data source.
     */
    public static void useDefaultLocalDataSource() {
        CONTEXT_HOLDER.set(DataSourceKey.defaultLocal.name());
    }

    /**
     * Use Dynamic data source.
     */
    public static void useDynamicDatasource(String datasourceKey) {
        lock.lock();

        try {
            if(EnumUtils.isValidEnum(DataSourceKey.class, datasourceKey)){
                CONTEXT_HOLDER.set(datasourceKey);
            }else {
                useDefaultLocalDataSource();
            }
        } catch (Exception e) {
            logger.error("Switch DynamicDatasource datasource failed, error message is {}", e.getMessage());
            useDefaultLocalDataSource();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * Check if give DataSource is in current DataSource list
     *
     * @param key the key
     * @return boolean boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
