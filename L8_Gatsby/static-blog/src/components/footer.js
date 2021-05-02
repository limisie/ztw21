import * as React from "react"

const Footer = ({author}) => (
    <footer className="foot">
        <p>Created by {author} © {new Date().getFullYear()}, Built with {` `} <a href="https://www.gatsbyjs.com">Gatsby</a></p>
    </footer>
)

export default Footer