import React from 'react'
import MenuItem from './menuItem'
import MenuTree from './menuTree'

export default props => (
    <ul className='sidebar-menu'>
        <MenuItem path='#' label='Dashboard' icon='dashboard' />
        <MenuTree label='Sales' icon='edit'> 
            <MenuItem path='#newSale'
                label='Add' icon='usd' />
        </MenuTree>
    </ul>
)