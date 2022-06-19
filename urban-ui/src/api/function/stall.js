import request from '@/utils/request'

// 查询摊位信息列表
export function listStall(query) {
  return request({
    url: '/function/stall/list',
    method: 'get',
    params: query
  })
}

// 查询摊位信息详细
export function getStall(stallsId) {
  return request({
    url: '/function/stall/' + stallsId,
    method: 'get'
  })
}

// 新增摊位信息
export function addStall(data) {
  return request({
    url: '/function/stall',
    method: 'post',
    data: data
  })
}

// 修改摊位信息
export function updateStall(data) {
  return request({
    url: '/function/stall',
    method: 'put',
    data: data
  })
}

// 删除摊位信息
export function delStall(stallsId) {
  return request({
    url: '/function/stall/' + stallsId,
    method: 'delete'
  })
}
//导入下载模板
export function importTemplate() {
  return request({
    url: '/function/stallInfo/importTemplate',
    method: 'get'
  })
}
