export const themeColors = {
    LIGHT_BLUE: {
        '--theme-color-header-bg' : '--color-blue',
        '--theme-color-menu-bg' : '--color-orange',
        '--theme-color-footer-bg' : '--color-yellow',
        '--theme-color-background' : '--color-olive',
    },
    BLACK_WHITE: {
        '--theme-color-header-bg' : '--color-gray-light',
        '--theme-color-menu-bg' : '--color-gray-active',
        '--theme-color-footer-bg' : '--color-navy',
        '--theme-color-background' : '--color-gray-light',
    }
};

/**
 * Function to change theme colors of application
 *
 * @param theme
 */
export default function changeTheme(theme) {
    let root = document.documentElement;

    theme && Object.keys(theme).map((key) => {
        return root.style.setProperty(key, getComputedStyle(root).getPropertyValue(theme[key]));
    })
}