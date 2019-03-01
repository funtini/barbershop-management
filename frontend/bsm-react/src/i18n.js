import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import dashboard_en from 'shared/translations/en/pages';
import report_en from "./shared/translations/en/validation";

i18n
    .use(initReactI18next)
    .init({
        fallbackLng: 'en',
        ns: [
            'common',
            'pages',
            'validation'
        ],
        defaultNS: 'common',
        debug: true,
        interpolation: {
            escapeValue: false, // not needed for react as it escapes by default
        },
        lng: 'en',
        resources: {
            en: {
                pages: dashboard_en,
                common: {
                    report: report_en,
                }
            },
            de: {
                translation: {

                }
            },
        }});


export default i18n;