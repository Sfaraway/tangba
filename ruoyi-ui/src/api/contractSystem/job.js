import request from '@/utils/request'

// 查询职位列表
export function listJob(query) {
  return request({
    url: '/tangba/job/list',
    method: 'get',
    params: query
  })
}

// 查询职位详细
export function getJob(jobId) {
  return request({
    url: '/tangba/job/' + jobId,
    method: 'get'
  })
}

// 新增职位
export function addJob(data) {
  return request({
    url: '/tangba/job',
    method: 'post',
    data: data
  })
}

// 修改职位
export function updateJob(data) {
  return request({
    url: '/tangba/job',
    method: 'put',
    data: data
  })
}

// 删除职位
export function delJob(jobId) {
  return request({
    url: '/tangba/job/' + jobId,
    method: 'delete'
  })
}
