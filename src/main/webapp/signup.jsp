<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Signup</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <div class="bg-gray-100 min-h-screen flex items-center justify-center">
        <div class="max-w-sm rounded-lg shadow-lg bg-white p-6 space-y-6 border border-gray-200 dark:border-gray-700">
            <div class="space-y-2 text-center">
                <h1 class="text-3xl font-bold">Sign up</h1>
                <p class="text-zinc-500 dark:text-zinc-400">By signing up, you accept our <a class="text-blue-500 hover:text-blue-700" href="#">terms </a>and <a class="text-blue-500 hover:text-blue-700" href="#">privacy policy</a>.</p>
            </div>
            <div class="space-y-4">
    
                <div class="space-y-2">
                    <form action="signup" method="post">
                        <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70" for="Nom">Last Name</label>
                        <input class="flex h-10 w-full mt-2 mb-8 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="nom" placeholder="Bennani" name="nom" required="Please Insert your last name" type="text" >
                        <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70" for="email">First Name</label>
                        <input class="flex h-10 w-full mt-2 mb-8 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="prenom" placeholder="said" name="prenom" required="Please Insert your first name" type="text" >
                        <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70" for="email">Email</label>
                        <input class="flex h-10 w-full mt-2 mb-8 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="email" placeholder="m@example.com" name="email" required="" type="email" >
                        <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70" for="password">Password</label>
                        <input class="flex h-10 w-full mt-2 mb-8 rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="password" placeholder="yourPassword" name="password" type="password" >
                        <input class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2 w-full bg-black text-white" type="submit" value="SignUp">
                    </form>
                </div>
    
                <div class="flex items-center space-x-2">
                        <hr class="flex-grow border-zinc-200 dark:border-zinc-700">
                            <span class="text-zinc-400 dark:text-zinc-300 text-sm">OR</span>
                        <hr class="flex-grow border-zinc-200 dark:border-zinc-700">
                    </div>
                <form action="login" method="post">
                    <input type="hidden" value="google" name="type">
                    <input class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2 w-full bg-[#4285F4] text-white" type="submit" value="Sign Up with google">
                </form>
            </div>
        </div>
    </div>
>

</body>
</html>
