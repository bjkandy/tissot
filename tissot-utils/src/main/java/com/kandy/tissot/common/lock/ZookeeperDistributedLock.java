//package com.kandy.tissot.common.lock;
//
//import com.alibaba.fastjson.JSON;
//import com.kandy.tissot.core.utils.AssertUtil;
//import com.kandy.tissot.core.utils.DateUtil;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.ZooDefs;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.Stat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.net.InetAddress;
//import java.util.Date;
//
///**
// * Created by kandy on 2020/9/29.
// */
//public class ZookeeperDistributedLock {
//    private static final Logger logger = LoggerFactory.getLogger(ZookeeperDistributedLock.class);
//
//    public static Boolean lock(ZooKeeper zooKeeper,String lockKey,String lockId){
//
//        return Boolean.FALSE;
//    }
//
//    public static Boolean unlock(ZooKeeper zooKeeper,String lockKey,String lockId){
//
//        return Boolean.FALSE;
//    }
//
//    public static Boolean checkLock(ZooKeeper zooKeeper,String lockKey,String lockId){
//
//        return Boolean.TRUE;
//    }
//
//    private LockResultEnum createZooKeeperNode(ZooKeeper zooKeeper,String parentPath, String lockId, String businessId) {
//        AssertUtil.notNull(parentPath, "锁路径不能为空");
//        AssertUtil.notNull(lockId, "锁id不能为空");
//
//        String lockPath = new StringBuilder(parentPath).append("/").append(lockId).toString();
//        try {
//            Stat stat = zooKeeper.exists(lockPath, false);
//            if (stat != null) {
//                return LockResultEnum.LOCK_FAIL;
//            }
//
//            // 生成锁信息串
//            String valueStr = generateLockInfo(lockId, businessId);
//            /**
//             * zk同步创建路径
//             * zooKeeper.create(java.lang.String path, byte[] data, java.util.List<org.apache.zookeeper.data.ACL> acl, org.apache.zookeeper.CreateMode createMode)
//             * 1、path：创建节点路径,需保证父节点已存在
//             * 2、data：节点数据
//             * 3、acl：权限列表 默认的权限OPEN_ACL_UNSAFE(完全开放)、CREATOR_ALL_ACL(创建该znode的连接拥有所有权限)、READ_ACL_UNSAFE(所有的客户端都可读)
//             * 4、createMode：节点类型 PERSISTENT(持久化节点)、PERSISTENT_SEQUENTIAL(持久化有序节点)、EPHEMERAL(临时节点，连接断开自动删除)、EPHEMERAL_SEQUENTIAL(临时有序节点,连接断开自动删除)
//             */
//            String nodeStr = zooKeeper.create(lockPath, valueStr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            logger.info("create zookeeper node " + lockPath);
//            return LockResultEnum.LOCK_SUCCESS;
//        } catch (Exception e) {
//            if (e instanceof KeeperException.NodeExistsException) {
//                // KeeperException keeperException = (KeeperException) e;
//                // if (keeperException.code().equals(Code.NODEEXISTS)) {
//                logger.warn(e.getMessage());
//                // }
//            } else {
//                logger.error(e.getMessage(), e);
//            }
////            setExpireSession(e);
//            // logger.error(e.getMessage(), e);
//            return LockResultEnum.EXCEPTION;
//        }
//    }
//
//    private Boolean deleteZookeeperNode(ZooKeeper zooKeeper, String lockKey, String lockId){
//        AssertUtil.notNull(zooKeeper,"ZK不能为空");
//        AssertUtil.notNull(lockKey,"锁Key不能为空");
//        AssertUtil.notNull(lockId,"锁ID不能为空");
//
//        try{
//            Stat stat = zooKeeper.exists(lockKey,Boolean.FALSE);
//            if (null == stat){
//                return Boolean.FALSE;
//            }
//
//            zooKeeper.delete(lockKey, -1);
//
//            return Boolean.TRUE;
//        }catch (Exception e){
//            logger.error("ZK删除节点失败" + e.getMessage());
//        }
//        return Boolean.FALSE;
//    }
//
//    /**
//     * 封装锁信息
//     * @param lockId
//     * @param businessId
//     * @return
//     */
//    private String generateLockInfo(String lockId, String businessId) {
//        LockInfo lockInfo = new LockInfo();
//        lockInfo.setLockId(lockId);
//        lockInfo.setBusinessId(businessId);
//
//        Date date = new Date();
//        lockInfo.setLockTimeStr(DateUtil.date2String(date,DateUtil.DEFAULT_DATETIME_FORMAT));
//        lockInfo.setLockTimeMills(date.getTime());
//
//        lockInfo.setIp(getLocalHostIP());
//        lockInfo.setThreadId(Thread.currentThread().getId());
//
//        return JSON.toJSONString(lockInfo);
//    }
//    private String getLocalHostIP() {
//        String ip = "";
//        try {
//            ip = InetAddress.getLocalHost().getHostAddress();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return ip;
//    }
//
//    /**
//     * 锁结果
//     */
//    public enum LockResultEnum {
//        LOCK_SUCCESS(0, "加锁成功"),
//        LOCK_FAIL(1, "加锁失败"),
//        UNLOCK_SUCCESS(2, "解锁成功"),
//        UNLOCK_FAIL(3, "解锁失败"),
//        LOCK_EXIST(4, "锁存在"),
//        LOCK_NOT_EXIST(5, "锁不存在"),
//        EXCEPTION(99, "操作异常");
//
//        /**
//         * 枚举值
//         */
//        private int code;
//
//        /**
//         * 枚举描述
//         */
//        private String desc;
//
//        LockResultEnum(int code, String desc) {
//            this.code = code;
//            this.desc = desc;
//        }
//    }
//}
//
//class LockInfo {
//    private String lockId;
//    private String businessId;
//    private long lockTimeMills;
//    private String lockTimeStr;
//    private String ip;
//    private long threadId;
//
//    public String getLockId() {
//        return lockId;
//    }
//
//    public void setLockId(String lockId) {
//        this.lockId = lockId;
//    }
//
//    public String getBusinessId() {
//        return businessId;
//    }
//
//    public void setBusinessId(String businessId) {
//        this.businessId = businessId;
//    }
//
//    public long getLockTimeMills() {
//        return lockTimeMills;
//    }
//
//    public void setLockTimeMills(long lockTimeMills) {
//        this.lockTimeMills = lockTimeMills;
//    }
//
//    public String getLockTimeStr() {
//        return lockTimeStr;
//    }
//
//    public void setLockTimeStr(String lockTimeStr) {
//        this.lockTimeStr = lockTimeStr;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public long getThreadId() {
//        return threadId;
//    }
//
//    public void setThreadId(long threadId) {
//        this.threadId = threadId;
//    }
//}
