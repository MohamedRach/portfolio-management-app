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
    <style>
        *{
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<!--
// v0 by Vercel.
// https://v0.dev/t/jgjBpvBWQzg
-->
    <div class="bg-white">
        <nav class="flex justify-between items-center w-full mb-6 mt-0 bg-[#0B1141]">
            <h1 class="px-8 py-4 text-2xl font-semibold text-white">InvesTogether</h1>
            <div class="px-8 py-4 flex space-x-4">
                <button type="button" role="combobox" aria-controls="radix-:r3d:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-16 w-full items-center justify-between rounded-md border border-input bg-white text-black px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="portfolio">
                    <span style="pointer-events: none;">portfolios</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
                <button type="button" role="combobox" aria-controls="radix-:r3e:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-white text-black px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="account">
                    <span style="pointer-events: none;">Consultants</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
                <button type="button" role="combobox" aria-controls="radix-:r3e:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="flex h-10 w-full items-center justify-between rounded-md border border-input bg-white text-black px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="account">
                    <span style="pointer-events: none;">Settings</span>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4 opacity-50" aria-hidden="true"><path d="m6 9 6 6 6-6"></path></svg>
                </button>
            </div>
        </nav>
        
        <div class="px-8 flex justify-between items-center mb-4">
            <div class="text-3xl font-bold flex justify-between items-center"> 
                <%= request.getAttribute("stockName")%>
                <div>
                    <button type="button" role="combobox" aria-controls="radix-:r3e:" aria-expanded="false" aria-autocomplete="none" dir="ltr" data-state="closed" data-placeholder="" class="ml-4 h-10 w-full bg-[#0B1141] text-white justify-between rounded-md border border-input px-3 py-2 text-sm ring-offset-background placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50" id="account">
                        <span style="pointer-events: none;">Add Stock to portfolio</span>
                    </button>
                </div>
            </div>
            <div class="text-3xl font-bold text-[#bd1e59]">90,348.31</div>
        </div>
        <div class="px-8 flex space-x-6 mb-6">
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
        <div class="px-8 flex items-center mb-6">
            <label class="flex items-center mr-4">
                <input class="form-checkbox" type="checkbox">
                <span class="ml-2">Showing open &amp; closed positions</span>
            </label>
            <label class="flex items-center">
                <input class="form-checkbox" type="checkbox">
                <span class="ml-2">Showing percentage gains</span>
            </label>
        </div>
        <div class="px-8 w-full h-[300px] mb-6">
            <div class="chart-container"style="width: 100%; height: 100%;">
                <canvas id="line-chart"></canvas>
            </div>
        </div>
        <div class="px-8 relative w-full overflow-auto">
            <table class="w-full caption-bottom text-sm">
                <thead class="[&amp;_tr]:border-b">
                    <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">Stock Name</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">Market Cap</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">DIVIDEND YIELD</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">Previouse Close</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">YEAR RANGE</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">DAY RANGE</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">CURRENCY</th>
                        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">RETURN</th>
                    </tr>
                </thead>
                <tbody class="[&amp;_tr:last-child]:border-0">
                    <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0"> <%= request.getAttribute("stockName")%></td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">194.50</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">372</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">72,354.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">54,235.74</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">1,554.03</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">0.00</td>
                        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">55,789.77</td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script src="./js/chart.js"></script>
    <stock-chart data="[[2023-12-05 19:20:00, 238.9450], [2023-12-05 19:05:00, 242.3400], [2023-12-05 15:50:00, 239.0500], [2023-12-05 12:10:00, 238.4580], [2023-12-05 16:20:00, 239.2210], [2023-12-05 15:35:00, 241.0050], [2023-12-05 12:50:00, 238.9500], [2023-12-05 16:40:00, 239.9250], [2023-12-05 17:10:00, 245.0050], [2023-12-05 12:15:00, 238.9600], [2023-12-05 14:10:00, 239.1700], [2023-12-05 15:15:00, 238.9280], [2023-12-05 17:30:00, 239.3100], [2023-12-05 14:20:00, 239.5500], [2023-12-05 13:15:00, 239.4000], [2023-12-05 15:00:00, 239.3300], [2023-12-05 12:35:00, 239.0650], [2023-12-05 17:25:00, 239.8850], [2023-12-05 14:40:00, 243.4700], [2023-12-05 18:30:00, 239.2990], [2023-12-05 15:45:00, 239.0650], [2023-12-05 18:55:00, 239.1300], [2023-12-05 19:10:00, 239.7900], [2023-12-05 19:35:00, 238.7600], [2023-12-05 11:50:00, 241.2400], [2023-12-05 17:55:00, 239.0300], [2023-12-05 14:35:00, 239.0200], [2023-12-05 15:05:00, 238.3600], [2023-12-05 14:55:00, 240.5800], [2023-12-05 19:15:00, 241.2900], [2023-12-05 19:30:00, 238.8900], [2023-12-05 17:00:00, 239.0000], [2023-12-05 16:30:00, 243.2540], [2023-12-05 12:25:00, 239.9700], [2023-12-05 14:00:00, 239.2100], [2023-12-05 15:25:00, 239.6250], [2023-12-05 16:50:00, 242.9900], [2023-12-05 19:45:00, 239.3000], [2023-12-05 12:45:00, 239.1300], [2023-12-05 13:50:00, 239.0500], [2023-12-05 14:30:00, 239.3000], [2023-12-05 16:15:00, 238.8500], [2023-12-05 13:05:00, 239.8730], [2023-12-05 13:45:00, 239.7500], [2023-12-05 19:00:00, 239.6400], [2023-12-05 17:15:00, 244.1300], [2023-12-05 14:50:00, 238.8500], [2023-12-05 15:55:00, 238.1500], [2023-12-05 13:00:00, 242.8800], [2023-12-05 13:25:00, 239.6150]]"></stock-chart>
</body>
</html>