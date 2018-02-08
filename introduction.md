1. 提供REST接口接收APP的device token，并将其存于DB: com.ps.apns.controller.TokenController
2. 发送日志服务： com.ps.apns.service.APNSService
3. DAO层基于Mybatis