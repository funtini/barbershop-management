import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

// English translations
import dashboard_en from 'shared/translations/en/dashboard';
import customers_en from 'shared/translations/en/customers';
import validation_en from "./shared/translations/en/validation";
import common_en from './shared/translations/en/common';
import sidebar_en from './shared/translations/en/sidebar';

// Portuguese translations
import dashboard_pt from 'shared/translations/pt/dashboard';
import customers_pt from 'shared/translations/pt/customers';
import validation_pt from "./shared/translations/pt/validation";
import common_pt from './shared/translations/pt/common';
import sidebar_pt from './shared/translations/pt/sidebar';


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
                pages: {
                    dashboard: dashboard_en,
                    customers: customers_en
                },
                common: common_en,
                validation: validation_en,
                sidebar: sidebar_en,
            },
            pt: {
                pages: {
                    dashboard: dashboard_pt,
                    customers: customers_pt,
                },
                common: common_pt,
                validation: validation_pt,
                sidebar: sidebar_pt,
            },
        }});


export default i18n;