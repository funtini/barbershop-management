import get from 'lodash/get';

export function getCurrentTheme(state) {
    return get(state, 'layout.theme');
}

