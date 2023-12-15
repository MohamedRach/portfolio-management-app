class SideBar extends HTMLElement {
    menu;
    shadow;
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = `
      <link rel="stylesheet" href="../css/sideBar.css">
      
    `;

        switch (this.getAttribute("menu")){
            case "user":
                this.menu= ["Portfolios", "Consultants", "Profile"];
                break;
            case "consultants":
                this.menu = ["Portfolios", "Clients","Reports", "Settings"];
                break;
            case "admin":
                this.menu = ["Dashboard", "Users","Consultants" ,"Settings"];
        }

    }

    createElement() {
        var template = document.createElement("template");
       var content = `
        <div class="sideBar">
            <div class="logoSection">
              <h1
                class="logo"
              >
                InvesTogether
              </h1>
            </div>
            <form class="form" method="POST" action="stock">
                <input class="stockSearch" type="text" placeholder="search a stock" name="name">
            </form>
            <ul class="menu">
            
            
          `
        this.menu.forEach((Menu)=> {
            //console.log("im here")
            content += ` 
            <li class="menuItem">
              <a  class="link" href="#">
              <span class="title">
                ${Menu}
              </span>
              </a>
              `

        })
        content += `
                </li>
            </ul>
        </div>
        `

        template.innerHTML = content
        return template

    }

    connectedCallback() {
        //this.loadCss();
        const template = this.createElement();
        const instance = template.content.cloneNode(true);
        this.shadowRoot.appendChild(instance);




    }
}
customElements.define("side-bar", SideBar);