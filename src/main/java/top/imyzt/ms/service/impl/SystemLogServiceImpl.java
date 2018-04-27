package top.imyzt.ms.service.impl;

import top.imyzt.ms.model.SystemLog;
import top.imyzt.ms.dao.SystemLogMapper;
import top.imyzt.ms.service.ISystemLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: imyzt
 * @date: 2018-04-27
 * @description: 系统日志表 服务实现类
 */
@Service("iSystemLogService")
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {

    @Autowired
    private SystemLogMapper mapper;
}
