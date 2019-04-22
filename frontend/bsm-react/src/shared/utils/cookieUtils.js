// Create cookie
export const setCookie = (name, value, days) => {
    let expires;

    if (days) {
        const date = new Date();

        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = `; expires=${date.toGMTString()}`;
    } else {
        expires = '';
    }
    document.cookie = `${name}=${value}${expires}; path=/`;
};

// Read cookie
export const getCookie = (name) => {
    const nameEQ = `${name}=`;
    const ca = document.cookie.split(';');

    for (const item of ca) {
        let c = item;

        while (c.charAt(0) === ' ') {
            c = c.substring(1, c.length);
        }

        if (c.indexOf(nameEQ) === 0) {
            return c.substring(nameEQ.length, c.length);
        }
    }

    return null;
};

export const deleteCookie = (name) =>
    setCookie(name, '', -1);
