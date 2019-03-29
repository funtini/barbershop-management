import get from 'lodash/get';

export function getCurrentTheme(state) {
    return get(state, 'layout.theme');
}

export function getSidebarStatus(state) {
    return get(state, 'layout.sidebar.collapsed');
}

