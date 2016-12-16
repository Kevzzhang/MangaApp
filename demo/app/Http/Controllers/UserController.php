<?php

namespace App\Http\Controllers;

use App\Follow;
use Illuminate\Http\Request;

use App\Http\Requests;

use App\User;

use Hash;
use DB;
use Auth;

class UserController extends Controller
{

    private $user;
    private $response;
    private $follow;

    function __construct () {
        $this->user = new User;
        $this->follow =  new Follow();
        $this->response = array();
    }

    function doRegister (Request $request) {
    	if ($this->user->isExist($request['email'], $request['name'])) {
	    	$_userdatas = array(
	    		'name'     => $request['name'],
	    		'email'    => $request['email'],
	    		'password' => Hash::make($request['password'])
	    	);
	    	User::create($_userdatas);
            $_userdatas = $this->user->get($request['email']);
            $this->response['status'] = true;
            $this->response['message'] = 'register success.';
            $this->response['user']    = $_userdatas;

    	} else {
            $this->response['status'] = false;
            $this->response['message'] = 'user already exist';
        }
        return response()->json($this->response);
    }

    function doLogin (Request $request) {
    	$_userdatas = array(
    		'email'    => $request['email'],
    		'password' => $request['password']
    	);

    	if(Auth::attempt($_userdatas)) {
            $_user = $this->user->get($request['email']);
            $this->response['status'] = true;
            $this->response['message'] = 'login success.';
            $this->response['user']    = $_user;
        } else {
            $this->response['status'] = false;
            $this->response['message'] = 'email and password does not match.';
        }
        return response()->json($this->response);

    }

    function getUsers ($id, Request $request) {
        $_filter = $request['filter'];
        $_users = $this->user->getUsers($id, $_filter);

        if (count($_users) > 0) {
            $this->response['status'] = true;
            foreach ($_users as &$_user) {
                $_user->isfollowed  = $this->follow->detect($id, $_user->id) !== null;
                $this->response['users'][] = $_user;
            }
        } else {
            $this->response['status'] = false;
            $this->response['message'] = 'No user found.';
        }
        return response()->json($this->response);
    }



    public function doUpdateProfile (Request $datas) {
        $_updateduser =  User::where('id', $datas['id'])->get()->first();
        if ($datas['flag'] == '1') {
            $_updateduser = DB::select('UPDATE users SET name = "'. $datas['updatedvalue'] .'" WHERE id = '. $datas['id'] .'');
        } else if ($datas['flag'] == '2') {
            $_updateduser = DB::select('UPDATE users SET email = "'. $datas['updatedvalue'] .'" WHERE id = '. $datas['id'] .'');
        } else if ($datas['flag'] == '3') {
            $_updateduser = DB::select('UPDATE users SET bio = "'. $datas['updatedvalue'] .'" WHERE id = '. $datas['id'] .'');
        } else if ($datas['flag'] == '4') {
            if (!Hash::check($datas['currentvalue'], $_updateduser['password']))
                return response()->json(['message'=> 'Sorry, please check the current password.', 'code'=> '400']);
            $_updateduser->update([
                'password'=> Hash::make($datas['updatedvalue'])
            ]);
        }
        $_updateduser = User::where('id', 'like', $datas['id'])->first();
        $this->response['status'] = true;
         $this->response['user'] = $_updateduser;
         return response()->json($this->response);
        // return response()->json(['message'=> 'User updated success.', 'status'=> true, 'user'=> $_updateduser]);
    }

}
