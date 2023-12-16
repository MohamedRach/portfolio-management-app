<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stock Data</title>
    <link href="./css/stockPage.css" rel="stylesheet">
    <script defer src="js/sideBare.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<!--
// v0 by Vercel.
// https://v0.dev/t/jgjBpvBWQzg
-->
    <div class="bg-white p-8">
        <nav class="flex justify-between items-center mb-6">
            <h1 class="text-2xl font-semibold">InvesTTTTYogether</h1>
            <div class="flex space-x-4">
                <button type="button" role="combobox" aria-controls="radix-:r3d:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-4 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="portfolio">
                    <span style="pointer-events: none;">Your portfolio</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
                <button type="button" role="combobox" aria-controls="radix-:r3e:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="account">
                    <span style="pointer-events: none;">Consultants</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
                <button type="button" role="combobox" aria-controls="radix-:r3e:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="account">
                    <span style="pointer-events: none;">Settings</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
            </div>
        </nav>
        
        <div class="flex justify-between items-center mb-4">
            <div class="text-3xl font-bold">Your portfolio</div>
            <div class="text-3xl font-bold text-[#bd1e59]">90,348.31</div>
        </div>
        <div class="flex space-x-6 mb-6">
            <button type="button" role="combobox" aria-controls="radix-:r3f:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="timeframe">
                <span style="pointer-events: none;">In the last 5 years</span>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
            </button>
            <button type="button" role="combobox" aria-controls="radix-:r3g:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="graphType">
                <span style="pointer-events: none;">Graph value - stacked</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
            </button>
            <button type="button" role="combobox" aria-controls="radix-:r3h:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="markets">
                <span style="pointer-events: none;">Markets</span><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
            </button>
        </div>
        <div class="flex items-center mb-6">
            <label class="flex items-center mr-4">
                <input class="form-checkbox" type="checkbox">
                <span class="ml-2">Showing open &amp; closed positions</span>
            </label>
            <label class="flex items-center">
                <input class="form-checkbox" type="checkbox">
                <span class="ml-2">Showing percentage gains</span>
            </label>
        </div>
        <div class="w-full h-[300px] mb-6">
            <div style="width: 100%; height: 100%;"></div>
        </div>
        <div class="relative w-full overflow-auto">
            <table class="w-full caption-bottom text-sm">
                <thead class="[&amp;_tr]:border-b">
                    <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">MARKET</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">PRICE</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">QTY</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">VALUE</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">CAPITAL GAINS</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">DIVIDENDS</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">CURRENCY</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">RETURN</th>
                    </tr>
                </thead>
                <tbody class="[&amp;_tr:last-child]:border-0">
                    <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">APPL.NASDAQ Apple Inc</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">194.50</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">372</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">72,354.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">54,235.74</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">1,554.03</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">0.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">55,789.77</td>
                    </tr>
                    <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">AMZN.NASDAQ Amazon.com Inc.</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">128.15</td><td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">160</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">20,504.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">-7,583.84</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">0.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">0.00</td>
                        
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">-7,583.84</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>