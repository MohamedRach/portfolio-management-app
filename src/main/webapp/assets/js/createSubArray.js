function createSubArrayItems() {
    const subMenu = document.getElementById("subMenu");
    console.log(globalState.getState())
    // Iterate over the array and create <li> elements
    globalState.getState().forEach(item => {
        const liElement = document.createElement("li");
        liElement.className = "menu-item";

        const aElement = document.createElement("a");
        aElement.href = "/portfolio?id="+item[0];
        aElement.className = "menu-link";

        const divElement = document.createElement("div");
        divElement.textContent = item[1];

        aElement.appendChild(divElement);
        liElement.appendChild(aElement);
        subMenu.appendChild(liElement);
    });
}
createSubArrayItems()