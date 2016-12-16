<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::post('user/register', 'UserController@doRegister');

Route::post('user/login', 'UserController@doLogin');

Route::post('users/all/{id}', 'UserController@getUsers');
Route::post('user/update/{id}', 'UserController@doUpdateProfile');

Route::post('follow/{id}/{followingid}', 'FollowController@work');
Route::get('follow/getfollowing/{id}', 'FollowController@getfollowing');
Route::get('follow/getfollowers/{id}', 'FollowController@getfollowers');

Route::post('user/edit', 'UserController@editprofile');