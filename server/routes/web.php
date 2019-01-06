<?php

use Illuminate\Http\Request;
use App\Log;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::post("/tgif/mim", function (Request $request) {
    $data = json_decode($request->getContent());
    $log = new Log();
    $log->data1 = $data->data1;
    $log->data2 = $data->data2;
    $log->save();
    return $log;
});


Route::post("/tgif/mim_hash", function (Request $request) {
    $data = json_decode($request->getContent());

    //check hash
    $hash = hash_hmac("sha256",$data->data1.$data->data2,"samplekey");
    if ($data->hash != $hash) {
        return "Hash Not much";
    }
    $log = new Log();
    $log->data1 = $data->data1;
    $log->data2 = $data->data2;
    $log->save();
    return $log;
});


Route::post("/tgif/mim_hash_native", function (Request $request) {
    $data = json_decode($request->getContent());

    //check hash
    $hash = hash_hmac("sha256",$data->data1.$data->data2,"234213");
    if ($data->hash != $hash) {
        return "Hash Not much";
    }
    $log = new Log();
    $log->data1 = $data->data1;
    $log->data2 = $data->data2;
    $log->save();
    return $log;
});
