import * as React from "react"
import { useStaticQuery, graphql } from "gatsby"

import Layout from "../components/layout"
import Seo from "../components/seo"

const BlogPage = () => {
  const data = useStaticQuery(graphql`
    query {
      allMarkdownRemark(sort: {fields: [frontmatter___date], order: DESC}) {
        edges {
          node {
            frontmatter {
              title
              date
              author
            }
            timeToRead
            excerpt
          }
        }
      }
    }
  `)

  return (
    <Layout>
      <Seo title="Static blog" />
      <h1>Welcome to my static blog</h1>
      <ul>
        {data.allMarkdownRemark.edges.map((edge => {
          return (
            <li>
              <div className="post">
                <post className="date">{edge.node.frontmatter.date}</post>
                <h4 className="title">{edge.node.frontmatter.title}</h4>
                <div className="info">
                  <p>author: {edge.node.frontmatter.author}</p>
                  <p>time to read: {edge.node.timeToRead} min</p>
                </div>
                <p>{edge.node.excerpt}</p>
              </div>
            </li>
          )
        }))}
      </ul>
    </Layout>
  )
}

export default BlogPage
