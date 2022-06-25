package cn.dearth.vexmusic.core.service.impl;

import cn.dearth.vexmusic.core.dto.RoleDto;
import cn.dearth.vexmusic.core.dto.request.RoleCreateRequest;
import cn.dearth.vexmusic.core.entity.Role;
import cn.dearth.vexmusic.core.exception.BizException;
import cn.dearth.vexmusic.core.exception.ExceptionType;
import cn.dearth.vexmusic.core.mapper.RoleMapper;
import cn.dearth.vexmusic.core.repository.RoleRepository;
import cn.dearth.vexmusic.core.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author dearth
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleRepository roleRepository;

    @Resource
    RoleMapper roleMapper;

    @Override
    public RoleDto create(RoleCreateRequest roleCreateRequest) {
        return roleMapper.toDto(roleRepository.save(roleMapper.createEntity(roleCreateRequest)));
    }

    @Override
    public RoleDto get(String id) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return roleMapper.toDto(optionalRole.get());
    }
}
