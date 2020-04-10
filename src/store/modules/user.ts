/**
 * Vuex 用户登录信息模块
 */

// 创建命名空间, namespaced 注意有个d
export const namespaced = true;

export const state = {
    // 用户身份admin/coach/student
    user: {
        type: "",
        info: {}
    }
};

export const mutations = {
    setType(state: any, type: string) {
        state.user.type = type;
    },
    setInfo(state: any, info: object) {
        state.user.info = info;
    }
};

export const actions = {};
