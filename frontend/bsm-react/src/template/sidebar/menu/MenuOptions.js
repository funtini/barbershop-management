import React from 'react';

const menuOptions = (t) =>  {
    const menu = [
        {
            item: { label: t('menu-option.dashboard'), icon: 'tachometer-alt', },
            subItems:[{ label:t('submenu-option.dashboard.today'), icon: 'x' }],
        },
        { item: { label: t('menu-option.booking'), icon: 'tachometer-alt', } },
        { item: { label: t('menu-option.customers'), icon: 'tachometer-alt', } },
        { item: { label: t('menu-option.products'), icon: 'tachometer-alt', } },
        { item: { label: t('menu-option.sales'), icon: 'tachometer-alt', } },
        { item: { label: t('menu-option.business'), icon: 'tachometer-alt', },
            subItems: [
                { label: t('submenu-option.business.employers'), icon: 'x', },
                { label: t('submenu-option.business.expenses'), icon: 'x', },
                { label: t('submenu-option.business.marketing'), icon: 'x', },
                { label: t('submenu-option.business.control'), icon: 'x', },
                { label: t('submenu-option.business.statistics'), icon: 'x', },
            ]
        },
        { item: { label: t('menu-option.reports'), icon: 'tachometer-alt', },
            subItems: [
                { label: t('submenu-option.reports.current-month'), icon: 'x', },
                { label: t('submenu-option.reports.current-year'), icon: 'x', },
                { label: t('submenu-option.reports.general'), icon: 'x', },
            ]
        },
        { item: { label: t('menu-option.settings'), icon: 'tachometer-alt', } },
    ];


    return menu;
};

export default menuOptions;
