import React from 'react';

//components
import Menu from './menu/Menu';

//styles
import styles from './Sidebar.css';

const Sidebar = ( props ) => (
    <aside { ...props }>
        <section className={ styles.sidebarWrapper }>
            <Menu/>
        </section>
    </aside>
);

export default Sidebar;
