<?php
/**
 * David Bezalel Laoli (david.laoly@gmail.com)
 * 1 Des 2016
 */

namespace App\Http\Controllers;

use App\Follow;
use App\User;

class FollowController extends Controller {

    private $response;
    private $follow;
    private $user;

    function __construct() {
        $this->response = array();
        $this->follow = new Follow;
        $this->user = new User;
    }

    public function work ($userid, $followingid) {
        $_data = array(
            'user_id'=> $userid,
            'following_id'=> $followingid
        );

        $_user = $this->user->getbyid($userid);
        $_followinguser = $this->user->getbyid($followingid);

        $_found = $this->follow->detect($userid, $followingid);

        if (isset($_found)) {
            $this->follow->remove($userid, $followingid);
            $this->response['status'] = false;
            $this->response['message'] = $_user->name . ' unfollow ' . $_followinguser->name;
        } else {
            Follow::create($_data);
            $this->response['status'] = true;
            $this->response['message'] = $_user->name . ' follow ' . $_followinguser->name;
        }
        return response()->json($this->response);
    }

    public function getfollowing ($id) {
        $_followings = $this->follow->getfollowing($id);
        if (count($_followings) > 0) {
            $this->response['status'] = true;
            foreach ($_followings as &$_following) {
                $_user = $this->user->getbyid($_following->following_id);
                $_user->isfollowed = !($this->follow->detect($id, $_following->id) !== null);
                $this->response['users'][] = $_user;
            }
        } else {
            $this->response['status'] = false;
            $this->response['message'] = 'You follow no user.';
        }
        return response()->json($this->response);
    }

    public function getfollowers ($id) {
        $_followers = $this->follow->getfollowers($id);
        if (count($_followers) > 0) {
            $this->response['status'] = true;
            foreach ($_followers as &$_follower) {
                $_user = $this->user->getbyid($_follower->user_id);
                $_user->isfollowed = !($this->follow->detect($id, $_follower->id) !== null);
                $this->response['users'][] = $_user;
            }
        } else {
            $this->response['status'] = false;
            $this->response['message'] = 'Yo have no follower.';
        }
        return response()->json($this->response);
    }
}