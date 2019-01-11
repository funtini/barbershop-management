import React from 'react';

// template components
import Header from 'template/header';
import SideBar from 'template/sidebar';
import Footer from 'template/footer';

// styles
import styles from './layout.css';

const layout = ( props ) => (
    <div className={ styles.wrapper }>
        <Header className={ styles.header }/>
        <SideBar className={ styles.sidebar }/>
        <main className={ styles.content }>
                <div className={ styles.contentWrapper}>
            { props.children }
                </div>
        </main>
        <Footer className={ styles.footer }/>
    </div>
);

export default layout;
