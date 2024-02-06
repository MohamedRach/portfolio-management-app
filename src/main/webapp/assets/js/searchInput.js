function redirectToLinkOnFormSubmit() {
    // Get the form element
    var form = document.getElementById("searchForm");

    // Attach event listener for form submission
    form.addEventListener('submit', function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Get the value of the input inside the form
        var inputValue = form.querySelector('input[name="stockName"]').value;

        // Redirect to the specified link
        window.location.href = "/stock" + '?name=' + encodeURIComponent(inputValue);
    });
}
redirectToLinkOnFormSubmit()