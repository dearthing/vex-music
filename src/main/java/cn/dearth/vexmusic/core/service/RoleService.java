package cn.dearth.vexmusic.core.service;

import cn.dearth.vexmusic.core.dto.RoleDto;
import cn.dearth.vexmusic.core.dto.request.RoleCreateRequest;

/**
 * @author dearth
 */
public interface RoleService {


    RoleDto create(RoleCreateRequest roleCreateRequest);

    RoleDto get(String id);
}
