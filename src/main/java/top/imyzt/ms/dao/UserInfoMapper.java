package top.imyzt.ms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.imyzt.ms.model.UserInfo;

import java.util.List;


/**
 * @author imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:29
 * @description: 用户信息Mapper文件
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{

    List<UserInfo> selList(@Param("page")Pagination page);

}
