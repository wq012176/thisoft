import request from '@/utils/request'

// 查询料号基本资料档列表
export function listMtrl(query) {
  return request({
    url: '/stock/mtrl/list',
    method: 'get',
    params: query
  })
}

// 查询料号基本资料档详细
export function getMtrl(mtrlNo) {
  return request({
    url: '/stock/mtrl/' + mtrlNo,
    method: 'get'
  })
}

// 新增料号基本资料档
export function addMtrl(data) {
  return request({
    url: '/stock/mtrl',
    method: 'post',
    data: data
  })
}

// 修改料号基本资料档
export function updateMtrl(data) {
  return request({
    url: '/stock/mtrl',
    method: 'put',
    data: data
  })
}

// 删除料号基本资料档
export function delMtrl(mtrlNo) {
  return request({
    url: '/stock/mtrl/' + mtrlNo,
    method: 'delete'
  })
}

// 导出料号基本资料档
export function exportMtrl(query) {
  return request({
    url: '/stock/mtrl/export',
    method: 'get',
    params: query
  })
}