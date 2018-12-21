import React from 'react';

// template components
import Header from '../header';
import SideBar from '../sidebar';
import Footer from '../footer';

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