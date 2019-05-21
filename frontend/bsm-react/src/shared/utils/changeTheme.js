export const themeColors = {
    LIGHT_BLUE: {
        '--color-primary':'--color-blue-light',
        '--color-primary-active':'--color-blue-light-active',
        '--theme-color-primary-filled':'--color-white',
        '--theme-color-header-bg' : '--color-primary',
        '--theme-color-header-bg-hover' : '--color-primary-active',
        '--theme-color-header-hover':'--color-white',
        '--theme-color-header':'--color-gray-light',
        '--theme-color-background':'--color-gray-light',
    },
    BLACK_WHITE: {
        '--color-primary':'--color-white',
        '--color-primary-active':'--color-gray-light',
        '--theme-color-primary-filled':'--color-gray-dark',
        '--theme-color-header-bg' : '--color-gray-light',
        '--theme-color-header-bg-hover' : '--color-gray-light-active',
        '--theme-color-header-hover':'--color-white',
        '--theme-color-header':'--color-black',
        '--theme-color-background':'--color-white'
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
