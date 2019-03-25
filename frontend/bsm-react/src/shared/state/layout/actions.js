import  * as actionTypes from './actionTypes';

export function switchTheme(theme){
    return {
        type: actionTypes.THEME_SWITCH,
        payload: theme,
    };
}

export function expandSidebar(){
    return { type: actionTypes.SIDEBAR_EXPAND };
}

export function collapseSidebar(){
    return { type: actionTypes.SIDEBAR_COLLAPSE };
}
