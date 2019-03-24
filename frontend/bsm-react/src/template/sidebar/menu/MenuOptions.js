import React from 'react';

const menuOptions = (t) =>
    ([
        {item: { label: t('menu-option.dashboard'), icon: 'tachometer-alt', },
            subItems:[{ label:t('submenu-option.dashboard.today'), icon: 'x' }],
        },
        { item: { label: t('menu-option.booking'), icon: 'calendar-alt', } },
        { item: { label: t('menu-option.customers'), icon: 'users', } },
        { item: { label: t('menu-option.products'), icon: 'cut', } },
        { item: { label: t('menu-option.sales'), icon: 'dollar-sign', } },
        { item: { label: t('menu-option.business'), icon: 'briefcase', },
            subItems: [
                { label: t('submenu-option.business.employers'), icon: 'x', },
                { label: t('submenu-option.business.expenses'), icon: 'x', },
                { label: t('submenu-option.business.marketing'), icon: 'x', },
                { label: t('submenu-option.business.control'), icon: 'x', },
                { label: t('submenu-option.business.statistics'), icon: 'x', },
            ]
        },
        { item: { label: t('menu-option.reports'), icon: ['far','clipboard'], },
            subItems: [
                { label: t('submenu-option.reports.current-month'), icon: 'x', },
                { label: t('submenu-option.reports.current-year'), icon: 'x', },
                { label: t('submenu-option.reports.general'), icon: 'x', },
            ]
        },
        { item: { label: t('menu-option.settings'), icon: 'cogs', } },
    ]);

export default menuOptions;
