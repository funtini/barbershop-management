import { createResponsiveStateReducer } from 'redux-responsive';

const getIsTouchDevice = () => {
    return typeof window !== 'undefined' && (
        (('ontouchstart' in window)
            || (navigator.MaxTouchPoints > 0)
            || (navigator.msMaxTouchPoints > 0)));
};

const viewportReducer = createResponsiveStateReducer({
    xs: 500,
    sm: 768,
    md: 1024,
    lg: 1280,
    xl: 1440,
},{
    extraFields: () => ({
        isTouchDevice: getIsTouchDevice(),
    })
});

export default viewportReducer;
