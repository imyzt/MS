package top.imyzt.ms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.imyzt.ms.dao.UserInfoMapper;
import top.imyzt.ms.model.UserInfo;
import top.imyzt.ms.service.UserInfoService;

import java.util.List;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:32
 * @description: 用户信息基本操作实现类
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> selList(Page<UserInfo> page) {
        List<UserInfo> userInfos = userInfoMapper.selList(page);
        return userInfos;
    }
}
