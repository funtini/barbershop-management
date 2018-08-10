import React from 'react'
import Menu from './menu/menu'
import UserPainel from './userPainel'

export default props => (
    <aside className='main-sidebar'>
        <section className='sidebar'>
            <UserPainel/>
            <Menu />
        </section>
    </aside>
)