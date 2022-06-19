import request from '@/utils/request'

// 查询投诉举报列表
export function listComplain(query) {
  return request({
    url: '/function/complain/list',
    method: 'get',
    params: query
  })
}

// 查询投诉举报详细
export function getComplain(complainId) {
  return request({
    url: '/function/complain/' + complainId,
    method: 'get'
  })
}

// 新增投诉举报
export function addComplain(data) {
  return request({
    url: '/function/complain',
    method: 'post',
    data: data
  })
}

// 修改投诉举报
export function updateComplain(data) {
  return request({
    url: '/function/complain',
    method: 'put',
    data: data
  })
}

// 删除投诉举报
export function delComplain(complainId) {
  return request({
    url: '/function/complain/' + complainId,
    method: 'delete'
  })
}
