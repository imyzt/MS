package top.imyzt.ms.modular.system.service.impl;

import top.imyzt.ms.modular.system.model.SysUser;
import top.imyzt.ms.modular.system.mapper.SysUserMapper;
import top.imyzt.ms.modular.system.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * <p>
 *     用户信息 服务实现类
 * </p>
 *
 * @author: imyzt
 * @date: 2018-05-29
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
}
