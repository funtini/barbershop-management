import * as actionTypes from './actionTypes';
import changeTheme from 'shared/utils/changeTheme';
import { themeColors } from 'shared/utils/changeTheme';

// Available Theme Options
const THEME_LIGHT_BLUE = 'LIGHT_BLUE';
const THEME_BLACK_WHITE = 'BLACK_WHITE';
const availableThemes = [
    THEME_BLACK_WHITE,
    THEME_LIGHT_BLUE
];

const initialState = {
    theme: THEME_LIGHT_BLUE,
    sidebar: {
        collapsed: false,
    },
};

const switchTheme = (state, action) => {
    switch (action.payload) {
        case THEME_LIGHT_BLUE:
            changeTheme(themeColors.LIGHT_BLUE);
            return {
                ...state,
                theme: THEME_LIGHT_BLUE,
            };
        case THEME_BLACK_WHITE:
            changeTheme(themeColors.BLACK_WHITE);
            return {
                ...state,
                theme: THEME_BLACK_WHITE,
            };
        default:
            return state;
    }
};

const switchSidebarStatus = (state) => {
    return {
        ...state,
        sidebar: {
            collapsed: !state.sidebar.collapsed,
        }
    }
};

const layoutReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.THEME_SWITCH:
            return switchTheme(state, action);
        case actionTypes.SIDEBAR_EXPAND:
            return switchSidebarStatus(state);
        default:
            return state
    }
};

export default layoutReducer;
