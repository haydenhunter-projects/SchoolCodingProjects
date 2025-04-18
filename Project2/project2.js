// DOMContentLoaded verifies that the html has been loaded before
// attempting to run this script
// This script ultimately does not do anything (it doesnt store or send the data anywhere) and I am not sure if we
// were actually supposed to include a .js script for this project
// but I found it fun exploring into how to complete this part so I did it anyway.
document.addEventListener("DOMContentLoaded", () => {
    //link email from form to here
    const emailInput = document.getElementById("email");
    //link msg from from to here
    const messageInput = document.getElementById("msg");
    //link button from form to here
    const submitButton = document.getElementById("btnsubmit");

    // gray out the submit button to start
    submitButton.disabled = true;

    // verify user entered values into text boxes
    function validateForm() {
        const email = emailInput.value.trim();
        const message = messageInput.value.trim();

        // when the user has entered values into text boxes ungray the submit button
        submitButton.disabled = !(email && message);
    }

    // actively checks for inputs in both of the text boxes
    emailInput.addEventListener("input", validateForm);
    messageInput.addEventListener("input", validateForm);
});